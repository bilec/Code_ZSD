package gui.listeners.combobox

import code.dictionary.Dictionary
import code.table.FourPartTable
import code.table.Table
import code.table.TwoPartTable
import tools.Constants
import tools.FileReader
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JComboBox
import javax.swing.JTextArea

class ShowJComboBoxListener(private val showJTextArea: JTextArea): ActionListener {
    override fun actionPerformed(e: ActionEvent?) {
        val tableJComboBox = e?.source as JComboBox<*>
        val selectedItem = tableJComboBox.selectedItem as String

        var setText = ""

        if(selectedItem.contains(Constants.SHOW_ENTRY_DICTIONARY_PREFIX))
        {
            val fileName = selectedItem.replace("\\D+".toRegex(),"")
            setText = FileReader.readToString("zsd_dictionary/$fileName.txt")
        }
        else if(selectedItem.contains(Constants.SHOW_ENTRY_USER_DEFINED_WORDS))
        {
            setText = Dictionary.userDefinedWordsToString()
        }
        else if(selectedItem.contains(Constants.SHOW_ENTRY_TABLE_PREFIX) || selectedItem.contains(Constants.SHOW_ENTRY_RANDOM_TABLE_PREFIX))
        {
            val tableNumber = selectedItem.replace("\\D+".toRegex(),"").toInt()

            when(val table = Table.tables[tableNumber - 1])
            {
                is TwoPartTable -> {
                    when{
                        selectedItem.contains(Constants.SHOW_ENTRY_FIRST_PAGE_POSTFIX) -> setText = table.firstPartTableToString()
                        selectedItem.contains(Constants.SHOW_ENTRY_SECOND_PAGE_POSTFIX) -> setText = table.secondPartTableToString()
                    }
                }

                is FourPartTable -> {
                    when {
                        selectedItem.contains(Constants.SHOW_ENTRY_FIRST_PAGE_POSTFIX) -> setText = table.firstPartTableToString()
                        selectedItem.contains(Constants.SHOW_ENTRY_SECOND_PAGE_POSTFIX) -> setText = table.secondPartTableToString()
                        selectedItem.contains(Constants.SHOW_ENTRY_THIRD_PAGE_POSTFIX) -> setText = table.thirdPartTableToString()
                        selectedItem.contains(Constants.SHOW_ENTRY_FOURTH_PAGE_POSTFIX) -> setText = table.fourthPartTableToString()
                    }
                }
            }
        }

        showJTextArea.text = setText
        showJTextArea.grabFocus()
        showJTextArea.caretPosition = 0
    }
}