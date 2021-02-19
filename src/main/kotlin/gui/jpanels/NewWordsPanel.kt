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

    init {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)
        tabPanel.layout = FlowLayout()
        ioPanel.layout = BoxLayout(ioPanel, BoxLayout.Y_AXIS)

        cleanButton.addActionListener(CleanButtonListener(inputJTextArea, outputJTextArea))
        saveButton.addActionListener(SaveButtonListener(inputJTextArea, outputJTextArea))

        tabPanel.add(saveButton)
        tabPanel.add(cleanButton)

        inputJTextArea.border = BorderFactory.createTitledBorder(Constants.WORD_INPUT_LABEL)
        ioPanel.add(inputJTextArea)
        outputJTextArea.border = BorderFactory.createTitledBorder(Constants.CODE_INPUT_LABEL)
        ioPanel.add(outputJTextArea)


        add(tabPanel)
        add(ioPanel)
    }
}