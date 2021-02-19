package gui.listeners.button

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JTextArea

class CleanButtonListener(private val inputJTextArea: JTextArea,
                          private val outputJTextArea: JTextArea
): ActionListener {
    override fun actionPerformed(e: ActionEvent?) {
        inputJTextArea.text = ""
        outputJTextArea.text = ""
    }
}