package de.mybehr.projects.service.file

import de.mybehr.projects.model.FastAFile
import de.mybehr.projects.model.FileFormat
import org.biojava.nbio.core.sequence.ProteinSequence
import org.biojava.nbio.core.sequence.io.FastaReaderHelper
import java.io.File
import java.util.*

/**
 * @author Tobias Behr
 */
class FastAFileHandler : FileHandler<FastAFile> {
    override fun write(content: FastAFile, format: FileFormat, file: File) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun read(file: File, format: FileFormat): FastAFile {

        val proteinSequences = FastaReaderHelper.readFastaProteinSequence(file)

        val entries = extractFastAEntries(proteinSequences)
        return FastAFile(file.absolutePath, entries)
    }

    internal fun extractFastAEntries(proteinSequences: LinkedHashMap<String, ProteinSequence>): ArrayList<FastAFile.FastAEntry> {
        val entries = proteinSequences.entries
                .map { e -> FastAFile.FastAEntry(e.key, e.value.sequenceAsString) }
                .toCollection(arrayListOf())
        return entries
    }

}