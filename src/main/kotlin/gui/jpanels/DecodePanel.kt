package gui.jpanels

import gui.listeners.button.DecodeButtonListener
import tools.Constants
import javax.swing.BoxLayout
import javax.swing.JButton

class DecodePanel: AbstractCodePanel() {

    private val decodeButton = JButton(Constants.DECODE_BUTTON)

    init {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)

        decodeButton.addActionListener(DecodeButtonListener(inputJTextArea, outputJTextArea))

        tabPanel.add(decodeButton,0)

        add(tabPanel)
        add(ioPanel)
    }
}