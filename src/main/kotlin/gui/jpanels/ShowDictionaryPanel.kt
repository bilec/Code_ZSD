package gui.jpanels

import gui.listeners.combobox.ShowJComboBoxListener
import tools.Constants
import javax.swing.*

class ShowDictionaryPanel: JPanel() {

    private val showJTextArea = JTextArea()
    private val showJScrollPane = JScrollPane(showJTextArea)
    private val showJComboBox = JComboBox(Constants.SHOW_ENTRIES)

    init {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)

        showJTextArea.isEditable = false

        showJComboBox.addActionListener(ShowJComboBoxListener(showJTextArea))
        showJComboBox.maximumSize = showJComboBox.preferredSize
        showJComboBox.isLightWeightPopupEnabled = false

        val defaultListCellRenderer = DefaultListCellRenderer()
        defaultListCellRenderer.horizontalAlignment = DefaultListCellRenderer.CENTER
        showJComboBox.renderer = defaultListCellRenderer

        showJComboBox.selectedIndex = 0

        add(showJComboBox)
        add(showJScrollPane)
    }

}