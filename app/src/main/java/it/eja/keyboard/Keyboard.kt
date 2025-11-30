// Copyright (C) by Ubaldo Porcheddu <ubaldo@eja.it>

package it.eja.keyboard

import android.inputmethodservice.InputMethodService
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo

class Keyboard : InputMethodService(), KeyboardView.OnKeyboardActionListener {

    private lateinit var kv: KeyboardView
    private lateinit var qwertyKeyboard: Keyboard
    private lateinit var symbolKeyboard: Keyboard
    private lateinit var accentsKeyboard: Keyboard
    private var isCaps = false

    override fun onCreateInputView(): View {
        kv = layoutInflater.inflate(R.layout.input, null) as KeyboardView
        qwertyKeyboard = Keyboard(this, R.xml.keys)
        symbolKeyboard = Keyboard(this, R.xml.symbols)
        accentsKeyboard = Keyboard(this, R.xml.accents)

        kv.keyboard = qwertyKeyboard
        kv.setOnKeyboardActionListener(this)
        kv.isPreviewEnabled = true

        return kv
    }

    override fun onKey(primaryCode: Int, keyCodes: IntArray?) {
        val ic = currentInputConnection ?: return
        when (primaryCode) {
            Keyboard.KEYCODE_DELETE -> ic.deleteSurroundingText(1, 0)

            Keyboard.KEYCODE_SHIFT -> {
                isCaps = !isCaps
                qwertyKeyboard.isShifted = isCaps
                kv.invalidateAllKeys()
            }

            Keyboard.KEYCODE_DONE -> {
                val action = currentInputEditorInfo.imeOptions and EditorInfo.IME_MASK_ACTION
                if (action != EditorInfo.IME_ACTION_NONE) {
                    ic.performEditorAction(action)
                } else {
                    ic.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER))
                }
            }

            Keyboard.KEYCODE_MODE_CHANGE -> {
                kv.keyboard = if (kv.keyboard == qwertyKeyboard) symbolKeyboard else qwertyKeyboard
            }

            -10 -> kv.keyboard = accentsKeyboard

            9 -> ic.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_TAB))
            19 -> ic.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_UP))
            20 -> ic.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_DOWN))
            21 -> ic.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_LEFT))
            22 -> ic.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_RIGHT))

            23 -> {
                ic.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_CENTER))
                ic.sendKeyEvent(KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_CENTER))
            }

            else -> {
                if (primaryCode > 0) {
                    var code = primaryCode.toChar()
                    if (Character.isLetter(code) && isCaps && kv.keyboard == qwertyKeyboard) {
                        code = code.uppercaseChar()
                    }
                    ic.commitText(code.toString(), 1)
                }
            }
        }
    }

    override fun onPress(primaryCode: Int) {}
    override fun onRelease(primaryCode: Int) {}
    override fun onText(text: CharSequence?) {}
    override fun swipeLeft() {}
    override fun swipeRight() {}
    override fun swipeDown() {}
    override fun swipeUp() {}
}
