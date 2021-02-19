package gui.listeners.button

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JTextField
import kotlin.random.Random

class RandomNumberButtonListener(private val randomNumberJTextField: JTextField): ActionListener {
    override fun actionPerformed(e: ActionEvent?) {
        val stringBuilder = StringBuilder()
        val random = Random

        for (i in 1..5)
        {
            stringBuilder.append(random.nextInt(10))
        }

        randomNumberJTextField.text = stringBuilder.toString()
    }
}