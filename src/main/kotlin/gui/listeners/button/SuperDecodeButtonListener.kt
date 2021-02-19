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

class SuperDecodeButtonListener(private val inputJTextArea: JTextArea,
                                private val outputJTextArea: JTextArea,
                                private val randomNumberJTextField: JTextField
): ActionListener {
    override fun actionPerformed(e: ActionEvent?) {
        GlobalScope.launch {
            val input = inputJTextArea.text.trimEnd()
            val textCodeGroups = input.split(" ")

            if(!Checks.areCodeGroupsCorrect(textCodeGroups) || textCodeGroups.size < 2)
            {
                JOptionPane.showMessageDialog(MyFrame, Constants.WRONG_INPUT, Constants.WARNING, JOptionPane.WARNING_MESSAGE)
                return@launch
            }

            val superDecodedTextAndRandomNumber = Table.superDecode(input)
            outputJTextArea.text = superDecodedTextAndRandomNumber.first
            randomNumberJTextField.text = superDecodedTextAndRandomNumber.second
        }
    }
}