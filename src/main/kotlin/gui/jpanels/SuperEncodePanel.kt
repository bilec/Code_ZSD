package gui.jpanels

import gui.listeners.button.RandomNumberButtonListener
import gui.listeners.button.SuperEncodeButtonListener
import tools.Constants
import javax.swing.BoxLayout
import javax.swing.JButton

class SuperEncodePanel: AbstractSuperCodePanel() {

    private val superEncodeButton = JButton(Constants.SUPER_ENCODE_BUTTON)
    private val randomNumberButton = JButton(Constants.RANDOM_NUMBER_BUTTON)

    init {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)

        randomNumberButton.addActionListener(RandomNumberButtonListener(randomNumberJTextField))
        superEncodeButton.addActionListener(SuperEncodeButtonListener(inputJTextArea, outputJTextArea, randomNumberJTextField))

        tabPanel.add(superEncodeButton, 0)

        tableAndNumberPanel.add(randomNumberButton,1)

        add(tabPanel)
        add(tableAndNumberPanel)
        add(ioPanel)
    }
}