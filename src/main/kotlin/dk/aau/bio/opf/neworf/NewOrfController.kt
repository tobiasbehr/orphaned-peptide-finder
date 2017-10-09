package dk.aau.bio.opf.neworf

import dk.aau.bio.opf.model.AnalysisFinishedEvent
import dk.aau.bio.opf.model.CsvFile
import dk.aau.bio.opf.model.FileFormat
import dk.aau.bio.opf.service.analysis.NewOrfService
import dk.aau.bio.opf.service.file.CsvFileHandler
import dk.aau.bio.opf.service.file.FastAFileHandler
import tornadofx.Controller
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * @author Tobias Behr
 */
class NewOrfController : Controller() {

    val model: NewOrfModel by inject()

    val csvFileHandler: CsvFileHandler by di()

    val fastaHandler: FastAFileHandler by di()

    fun loadPeptidesHeader() = csvFileHandler.read(model.peptidesFileReference.value, FileFormat.MaxQuandt)

    fun loadFastAFile() = fastaHandler.read(model.referenceDbFileReference.value, FileFormat.MaxQuandt)

    fun startAnalysis() {
        runAsync {
            val newPeptides = NewOrfService(model.input).findNewPeptides()
            csvFileHandler.write(
                    CsvFile(listOf(CsvFile.HeaderColumn("Orphaned Peptide", 0), CsvFile.HeaderColumn("Accession", 1)), newPeptides),
                    FileFormat.MaxQuandt,
                    model.targetFolder.value,
                    getTargetFileName()
            )
        } ui {
            fire(AnalysisFinishedEvent)
        }
    }

    private fun getTargetFileName(): String {
        val formatter = DateTimeFormatter.ofPattern("YYYYMMdd_HHmm")
        val filename = model.peptidesFileReference.value.name

        return String.format("%s_%s.csv", filename.substring(0, filename.indexOf(".")), LocalDateTime.now().format(formatter))
    }
}