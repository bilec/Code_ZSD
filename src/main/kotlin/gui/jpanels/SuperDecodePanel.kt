package gui.jpanels

import gui.listeners.button.SuperDecodeButtonListener
import tools.Constants
import javax.swing.BoxLayout
import javax.swing.JButton

class SuperDecodePanel: AbstractSuperCodePanel() {

    private val superDecodeButton = JButton(Constants.SUPER_DECODE_BUTTON)

    init {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)

        superDecodeButton.addActionListener(SuperDecodeButtonListener(inputJTextArea,outputJTextArea, randomNumberJTextField))

        randomNumberJTextField.isEditable = false

        tabPanel.add(superDecodeButton, 0)

        add(tabPanel)
        add(tableAndNumberPanel)
        add(ioPanel)
    }
}