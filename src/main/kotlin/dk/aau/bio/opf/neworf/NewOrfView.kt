package dk.aau.bio.opf.neworf

import dk.aau.bio.opf.model.AnalysisFinishedEvent
import dk.aau.bio.opf.model.CsvFile
import dk.aau.bio.opf.util.FileToStringConverter
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.Label
import javafx.scene.layout.AnchorPane
import tornadofx.*

/**
 * @author Tobias Behr
 */
class NewOrfView : View("--- Orphan Peptide Finder ---") {
    override val root = AnchorPane()

    val model: NewOrfModel by inject()

    val controller: NewOrfController by inject()

    var peptidesColumnCombo: ComboBox<CsvFile.HeaderColumn> by singleAssign()
    var accessionColumnCombo: ComboBox<CsvFile.HeaderColumn> by singleAssign()
    var startAnalysisButton: Button by singleAssign()
    var cancelAnalysisButton: Button by singleAssign()
    var statusLabel: Label by singleAssign()

    init {

        subscribe<AnalysisFinishedEvent> {
            startAnalysisButton.setDisable(false)
            cancelAnalysisButton.setDisable(true)
            statusLabel.text = "Analysis finished, waiting for input ..."
        }

        with(root) {
            borderpane {
                center =
                        vbox {
                            spacing = 5.0
                            paddingBottom(20.toDouble())
                            paddingLeft(20.toDouble())
                            paddingRight(20.toDouble())
                            paddingTop(20.toDouble())
                            vbox {
                                spacing = 10.toDouble()
                                paddingTop(10.toDouble())
                                paddingBottom(10.toDouble())
                                paddingLeft(10.toDouble())
                                paddingRight(10.toDouble())
                                style = "-fx-border-color: darkblue; -fx-border-style: solid"
                                hbox {
                                    label("New Orf Data") {
                                        alignment = Pos.CENTER_LEFT
                                        prefWidth = 100.0
                                    }
                                    hbox {
                                        prefWidth = 170.0
                                        textfield(model.peptidesFileReference, FileToStringConverter()) {
                                            disableProperty().value = true

                                            validator { if (it.isNullOrBlank()) error("This field is required") else null }
                                        }
                                        button {
                                            text = "..."
                                            setOnAction {
                                                model.peptidesFileReference.value = chooseFile("Input File", arrayOf()).singleOrNull()
                                                if (model.peptidesFileReference.value != null) {
                                                    runAsync {
                                                        controller.loadPeptidesHeader()
                                                    } ui { csvFile ->
                                                        model.input.peptidesFile = csvFile
                                                        setPeptidesHeader(csvFile.header);
                                                    }
                                                }
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
                                        validator { if (it?.name.isNullOrBlank()) error("This field is required") else null }
                                    }
                                }
                                hbox {
                                    label("Accession") {
                                        alignment = Pos.CENTER_LEFT
                                        prefWidth = 100.0
                                    }
                                    accessionColumnCombo = combobox<CsvFile.HeaderColumn>(model.accessionColumn) {
                                        prefWidth = 170.0
                                        validator { if (it?.name.isNullOrBlank()) error("This field is required") else null }
                                    }
                                }
                            }

                            vbox {
                                spacing = 10.toDouble()
                                paddingTop(10.toDouble())
                                paddingBottom(10.toDouble())
                                paddingLeft(10.toDouble())
                                paddingRight(10.toDouble())
                                style = "-fx-border-color: darkblue; -fx-border-style: solid"
                                hbox {
                                    label("Reference DB") {
                                        alignment = Pos.CENTER_LEFT
                                        prefWidth = 100.0
                                    }
                                    hbox {
                                        prefWidth = 170.0
                                        textfield(model.referenceDbFileReference, FileToStringConverter()) {
                                            disableProperty().value = true

                                            validator { if (it.isNullOrBlank()) error("This field is required") else null }
                                        }
                                        button {
                                            text = "..."
                                            setOnAction {
                                                model.referenceDbFileReference.value = chooseFile("Reference DB", arrayOf()).singleOrNull()
                                                if (model.referenceDbFileReference.value != null) {
                                                    runAsync {
                                                        controller.loadFastAFile()
                                                    } ui { fastAFile ->
                                                        model.input.fastAFile = fastAFile
                                                        println("Read FastAFile: ${fastAFile.entries.size} entries!")
                                                    }
                                                }
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
                                    textfield("") {
                                        disableProperty().value = true
                                        alignment = Pos.CENTER_LEFT
                                    }
                                }
                            }

                            vbox {
                                spacing = 10.toDouble()
                                paddingTop(10.toDouble())
                                paddingBottom(10.toDouble())
                                paddingLeft(10.toDouble())
                                paddingRight(10.toDouble())
                                style = "-fx-border-color: darkblue; -fx-border-style: solid"
                                hbox {
                                    label("Target") {
                                        alignment = Pos.CENTER_LEFT
                                        prefWidth = 100.0
                                    }
                                    hbox {
                                        prefWidth = 170.0
                                        textfield(model.targetFolder, FileToStringConverter()) {
                                            disableProperty().value = true

                                            validator { if (it.isNullOrBlank()) error("This field is required") else null }
                                        }
                                        button {
                                            text = "..."
                                            setOnAction {
                                                model.targetFolder.value = chooseDirectory("Target Folder")
                                            }
                                        }
                                    }
                                }
                            }
                            vbox {
                                paddingTop(10.toDouble())
                                paddingBottom(10.toDouble())
                                paddingLeft(10.toDouble())
                                paddingRight(10.toDouble())
                                hbox {
                                    spacing = 5.0
                                    alignment = Pos.CENTER_RIGHT
                                    cancelAnalysisButton = button {
                                        prefWidth = 100.0
                                        isDisable = true
                                        text = "Cancel"
                                    }
                                    startAnalysisButton = button {
                                        prefWidth = 100.0
                                        isDisable = false
                                        text = "Start"
                                        setOnAction {
                                            model.commit()
                                            if (model.isValid) {
                                                startAnalysis()
                                                statusLabel.text = "Analysis running ..."
                                            }
                                        }
                                    }
                                }
                            }
                        }
                bottom =
                        hbox {
                            paddingLeft(15.0)
                            alignment = Pos.CENTER_LEFT
                            label("Status: ")
                            statusLabel = label("Waiting for input")
                        }
            }

        }
    }


    fun startAnalysis() {
        startAnalysisButton.setDisable(true)
        cancelAnalysisButton.setDisable(false)
        controller.startAnalysis()
    }

    fun setPeptidesHeader(header: List<CsvFile.HeaderColumn>) {
        peptidesColumnCombo.items.clear()
        accessionColumnCombo.items.clear()
        peptidesColumnCombo.items.addAll(header)
        accessionColumnCombo.items.addAll(header)
    }
}

