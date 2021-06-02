package gui.listeners.button

import code.dictionary.Dictionary
import gui.MyFrame
import tools.Checks
import tools.Constants
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JOptionPane
import javax.swing.JTextArea

class SaveButtonListener(private val wordJTextArea: JTextArea,
                         private val codeJTextArea: JTextArea
): ActionListener {
    override fun actionPerformed(e: ActionEvent?) {
        val word = wordJTextArea.text.trim()
        val code = codeJTextArea.text.trim()

        if(word.isBlank() && code.isBlank())
        {
            JOptionPane.showMessageDialog(MyFrame,Constants.WORD_AND_CODE_CANT_BE_BLANK,Constants.WARNING, JOptionPane.WARNING_MESSAGE)
            return
        }
        else if(word.isBlank())
        {
            JOptionPane.showMessageDialog(MyFrame,Constants.WORD_CANT_BE_BLANK,Constants.WARNING, JOptionPane.WARNING_MESSAGE)
            return
        }
        else if(code.isBlank())
        {
            JOptionPane.showMessageDialog(MyFrame,Constants.CODE_CANT_BE_BLANK,Constants.WARNING, JOptionPane.WARNING_MESSAGE)
            return
        }

        if(!Checks.areCodeGroupsCorrect(code.split(" ")))
        {
            JOptionPane.showMessageDialog(MyFrame, Constants.CODE_GROUP_ERROR,Constants.WARNING, JOptionPane.WARNING_MESSAGE)
            return
        }

        val dictionary = Dictionary
        dictionary.addUserDefinedWord(code, word)

        wordJTextArea.text = ""
        codeJTextArea.text = ""
    }
}