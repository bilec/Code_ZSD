package gui.jpanels

import gui.listeners.button.EncodeButtonListener
import tools.Constants
import javax.swing.BoxLayout
import javax.swing.JButton

class EncodePanel: AbstractCodePanel() {

    private val encodeButton = JButton(Constants.ENCODE_BUTTON)

    init {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)

        encodeButton.addActionListener(EncodeButtonListener(inputJTextArea, outputJTextArea))

        tabPanel.add(encodeButton,0)

        add(tabPanel)
        add(ioPanel)
    }
}