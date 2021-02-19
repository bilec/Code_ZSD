package gui.jpanels

import gui.listeners.combobox.TableJComboBoxListener
import tools.Constants
import java.awt.Dimension
import java.awt.FlowLayout
import javax.swing.DefaultListCellRenderer
import javax.swing.JComboBox
import javax.swing.JPanel
import javax.swing.JTextField

abstract class AbstractSuperCodePanel: AbstractCodePanel() {

    private val tableJComboBox = JComboBox(Constants.TABLES)

    val randomNumberJTextField = JTextField()

    val tableAndNumberPanel = JPanel()

    init {
        tableAndNumberPanel.layout = FlowLayout(FlowLayout.CENTER,5,0)

        tableJComboBox.addActionListener(TableJComboBoxListener())

        tableJComboBox.maximumSize = tableJComboBox.preferredSize
        tableJComboBox.isLightWeightPopupEnabled = false

        randomNumberJTextField.columns = 4
        randomNumberJTextField.size = Dimension(randomNumberJTextField.width, cleanButton.height)

        val defaultListCellRenderer = DefaultListCellRenderer()
        defaultListCellRenderer.horizontalAlignment = DefaultListCellRenderer.CENTER
        tableJComboBox.renderer = defaultListCellRenderer

        tableAndNumberPanel.add(randomNumberJTextField)
        tableAndNumberPanel.add(tableJComboBox)
    }

}