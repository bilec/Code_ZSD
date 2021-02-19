package gui.listeners.button

import code.Code
import gui.MyFrame
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tools.Checks
import tools.Constants
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JOptionPane
import javax.swing.JTextArea

class DecodeButtonListener(private val inputJTextArea: JTextArea,
                           private val outputJTextArea: JTextArea
): ActionListener {

    override fun actionPerformed(e: ActionEvent?) {
        GlobalScope.launch {
            val input = inputJTextArea.text.trimEnd()

            if(!Checks.areCodeGroupsCorrect(input.split(" ")))
            {
                JOptionPane.showMessageDialog(MyFrame, Constants.WRONG_INPUT, Constants.WARNING, JOptionPane.WARNING_MESSAGE)
                return@launch
            }

            outputJTextArea.text = Code.decode(input)
        }
    }
}