package gui.listeners.button

import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JTextArea

class PasteButtonListener(private val inputJTextArea: JTextArea): ActionListener {
    override fun actionPerformed(e: ActionEvent?) {
        val copiedData = Toolkit.getDefaultToolkit().systemClipboard.getData(DataFlavor.stringFlavor) as String
        inputJTextArea.text = copiedData
    }
}