package gui.listeners.button

import code.table.Table
import gui.MyFrame
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tools.Checks
import tools.Constants
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JOptionPane
import javax.swing.JTextArea
import javax.swing.JTextField

class SuperEncodeButtonListener(private val inputJTextArea: JTextArea,
                                private val outputJTextArea: JTextArea,
                                private val randomNumberJTextField: JTextField
): ActionListener {
    override fun actionPerformed(e: ActionEvent?) {
        GlobalScope.launch {
            val input = inputJTextArea.text.trimEnd()
            val randomNumber = randomNumberJTextField.text.trimEnd()

            if(!Checks.isCodeGroupCorrect(randomNumber))
            {
                JOptionPane.showMessageDialog(MyFrame, Constants.WRONG_RANDOM_NUMBER, Constants.WARNING, JOptionPane.WARNING_MESSAGE)
                return@launch
            }

            if(!Checks.areCodeGroupsCorrect(input.split(" ")))
            {
                JOptionPane.showMessageDialog(MyFrame, Constants.WRONG_INPUT, Constants.WARNING, JOptionPane.WARNING_MESSAGE)
                return@launch
            }

            outputJTextArea.text = Table.superEncode(input, randomNumber)
        }
    }
}