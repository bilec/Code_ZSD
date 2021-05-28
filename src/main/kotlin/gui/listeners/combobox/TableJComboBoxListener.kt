package gui.listeners.combobox

import code.table.Table
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JComboBox

class TableJComboBoxListener: ActionListener {
    override fun actionPerformed(e: ActionEvent?) {
        val tableJComboBox = e?.source as JComboBox<*>
        val selectedItem = tableJComboBox.selectedItem as String

        Table.selectedTable = Character.getNumericValue(selectedItem.last())
    }
}