package gui.jpanels

import gui.listeners.button.CleanButtonListener
import gui.listeners.button.CopyButtonListener
import gui.listeners.button.PasteButtonListener
import tools.Constants
import java.awt.FlowLayout
import javax.swing.*

abstract class AbstractCodePanel: JPanel() {

    val cleanButton = JButton(Constants.CLEAN_BUTTON)
    private val copyButton = JButton(Constants.COPY_BUTTON)
    private val pasteButton = JButton(Constants.PASTE_BUTTON)

    val tabPanel = JPanel()
    val ioPanel = JPanel()

    val inputJTextArea = JTextArea()
    val outputJTextArea = JTextArea()

    init {
        tabPanel.layout = FlowLayout()
        ioPanel.layout = BoxLayout(ioPanel, BoxLayout.Y_AXIS)

        outputJTextArea.isEditable = false

        cleanButton.addActionListener(CleanButtonListener(inputJTextArea, outputJTextArea))
        copyButton.addActionListener(CopyButtonListener(outputJTextArea))
        pasteButton.addActionListener(PasteButtonListener(inputJTextArea))

        tabPanel.add(cleanButton)
        tabPanel.add(copyButton)
        tabPanel.add(pasteButton)

        inputJTextArea.border = BorderFactory.createTitledBorder(Constants.INPUT_LABEL)
        ioPanel.add(inputJTextArea)
        outputJTextArea.border = BorderFactory.createTitledBorder(Constants.OUTPUT_LABEL)
        ioPanel.add(outputJTextArea)
    }
}