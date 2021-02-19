package gui.listeners.button

import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.ClipboardOwner
import java.awt.datatransfer.StringSelection
import java.awt.datatransfer.Transferable
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JTextArea

class CopyButtonListener(private val outputJTextArea: JTextArea): ActionListener, ClipboardOwner {
    override fun actionPerformed(e: ActionEvent?) {
        val stringSelection = StringSelection(outputJTextArea.text)
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard
        clipboard.setContents(stringSelection, this)
    }

    override fun lostOwnership(clipboard: Clipboard?, contents: Transferable?) {
    }
}