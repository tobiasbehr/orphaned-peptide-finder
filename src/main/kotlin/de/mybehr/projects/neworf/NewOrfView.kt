package de.mybehr.projects.neworf

import de.mybehr.projects.model.CsvFile
import de.mybehr.projects.util.FileToStringConverter
import javafx.geometry.Pos
import javafx.scene.control.ComboBox
import javafx.scene.layout.AnchorPane
import tornadofx.*

/**
 * @author Tobias Behr
 */
class NewOrfView : View("--- TITLE MISSING ---") {
    override val root = AnchorPane()

    val model: NewOrfModel by inject()

    val controller: NewOrfController by inject()

    var peptidesColumnCombo: ComboBox<CsvFile.HeaderColumn> by singleAssign()
    var accessionColumnCombo: ComboBox<CsvFile.HeaderColumn> by singleAssign()

    init {

        with(root) {
            vbox {
                vbox {
                    spacing = 10.toDouble()
                    paddingTop(10.toDouble())
                    paddingBottom(10.toDouble())
                    style = "-fx-border-color: darkblue; -fx-border-style: solid"
                    hbox {
                        label("New Orf Data") {
                            alignment = Pos.CENTER_LEFT
                            prefWidth = 100.0
                        }
                        hbox {
                            prefWidth = 170.0
                            textfield (model.peptidesFile, FileToStringConverter())
                            button {
                                text = "..."
                                setOnAction {
                                    model.peptidesFile.value = chooseFile("test", arrayOf()).singleOrNull()
                                    controller.loadPeptidesHeader()
                                }
                            }
                        }
                    }
                    hbox {
                        label("Peptides") {
                            alignment = Pos.CENTER_LEFT
                            prefWidth = 100.0
                        }
                        peptidesColumnCombo = combobox<CsvFile.HeaderColumn>(model.peptidesColumn) {
                            prefWidth = 170.0
                        }
                    }
                    hbox {
                        label("Accession") {
                            alignment = Pos.CENTER_LEFT
                            prefWidth = 100.0
                        }
                        accessionColumnCombo = combobox<CsvFile.HeaderColumn>(model.accessionColumn) {
                            prefWidth = 170.0
                        }
                    }
                }

                vbox {
                    spacing = 10.toDouble()
                    paddingTop(10.toDouble())
                    paddingBottom(10.toDouble())
                    style = "-fx-border-color: darkblue; -fx-border-style: solid"
                    hbox {
                        label("Reference DB") {
                            alignment = Pos.CENTER_LEFT
                            prefWidth = 100.0
                        }
                        hbox {
                            prefWidth = 170.0
                            textfield(model.referenceDbFile, FileToStringConverter())
                            button {
                                text = "..."
                                setOnAction {
                                    model.referenceDbFile.value = chooseFile("test", arrayOf()).singleOrNull()
                                }
                            }
                        }
                    }
                    hbox {
                        prefWidth = 170.0
                        label("Filetype") {
                            alignment = Pos.CENTER_LEFT
                            prefWidth = 100.0
                        }
                        textfield {
                            alignment = Pos.CENTER_LEFT

                        }
                    }
                }

                vbox {
                    spacing = 10.toDouble()
                    paddingTop(10.toDouble())
                    paddingBottom(10.toDouble())
                    style = "-fx-border-color: darkblue; -fx-border-style: solid"
                    hbox {
                        label ("Target") {
                            alignment = Pos.CENTER_LEFT
                            prefWidth = 100.0
                        }
                        hbox {
                            prefWidth = 170.0
                            textfield(model.targetFolder, FileToStringConverter())
                            button {
                                text = "..."
                                setOnAction {
                                    model.targetFolder.value = chooseDirectory("Target Folder")
                                }
                            }
                        }
                    }

                    hbox {
                        button { text = "Cancel" }
                        button {
                            text = "Start"
                        }
                    }
                    hbox {
                        label("Status:")
                        label("Waiting for input")
                    }
                }
            }
        }
    }

    fun setPeptidesHeader(header: List<CsvFile.HeaderColumn>) {
        peptidesColumnCombo.items.addAll(header)
        accessionColumnCombo.items.addAll(header)
    }
}

