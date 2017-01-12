package de.mybehr.projects

import tornadofx.*

/**
 * @author Tobias Behr
 */
class NewOrfView: View() {
    override val root = vbox {
        vbox {
            spacing = 10.toDouble()
            paddingTop(10.toDouble())
            paddingBottom(10.toDouble())
            style = "-fx-border-color: darkblue; -fx-border-style: solid"
            hbox {
                label("New Orf Data")
                hbox {
                    textfield { }
                    button {
                        text = "..."
                        setOnAction { println("Hallo Welt") }
                    }
                }
            }
            hbox {
                label("Peptides")
                combobox<String> { }
            }
            hbox {
                label("Accession")
                combobox<String> {  }
            }
        }

        vbox {
            spacing = 10.toDouble()
            paddingTop(10.toDouble())
            paddingBottom(10.toDouble())
            style = "-fx-border-color: darkblue; -fx-border-style: solid"
            hbox {
                label("Reference DB")
                hbox {
                    textfield { }
                    button {
                        text = "..."
                        setOnAction { println("refernceDb button clicked") }
                    }
                }
            }
            hbox {
                label("Filetype")
                textfield {  }
            }
        }

        vbox {
            spacing = 10.toDouble()
            paddingTop(10.toDouble())
            paddingBottom(10.toDouble())
            style = "-fx-border-color: darkblue; -fx-border-style: solid"
            hbox {
                label ("Target")
                hbox {
                    textfield { }
                    button {
                        text = "..."
                        setOnAction { println("Button target clicked") }
                    }
                }
            }

            hbox {
                button { text = "Cancel" }
                button { text = "Start" }
            }
            hbox {
                label("Status:")
                label("Waiting for input")
            }
        }
    }

}