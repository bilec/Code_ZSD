package gui.jpanels

import gui.listeners.button.CleanButtonListener
import gui.listeners.button.SaveButtonListener
import tools.Constants
import java.awt.FlowLayout
import javax.swing.*

class NewWordsPanel: JPanel() {

    private val saveButton = JButton(Constants.SAVE_BUTTON)
    private val cleanButton = JButton(Constants.CLEAN_BUTTON)

    private val tabPanel = JPanel()
    private val ioPanel = JPanel()

    val inputJTextArea = JTextArea()
    val outputJTextArea = JTextArea()

    private val inputJScrollPane = JScrollPane(inputJTextArea)
    private val outputJScrollPane = JScrollPane(outputJTextArea)

    init {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)
        tabPanel.layout = FlowLayout()
        ioPanel.layout = BoxLayout(ioPanel, BoxLayout.Y_AXIS)

        tabPanel.maximumSize = Constants.MAXIMUM_PANEL_SIZE

        cleanButton.addActionListener(CleanButtonListener(inputJTextArea, outputJTextArea))
        saveButton.addActionListener(SaveButtonListener(inputJTextArea, outputJTextArea))

        tabPanel.add(saveButton)
        tabPanel.add(cleanButton)

        inputJScrollPane.border = BorderFactory.createTitledBorder(Constants.WORD_INPUT_LABEL)
        ioPanel.add(inputJScrollPane)
        outputJScrollPane.border = BorderFactory.createTitledBorder(Constants.CODE_INPUT_LABEL)
        ioPanel.add(outputJScrollPane)


        add(tabPanel)
        add(ioPanel)
    }
}