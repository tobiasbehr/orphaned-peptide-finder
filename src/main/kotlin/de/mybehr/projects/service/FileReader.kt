package de.mybehr.projects.service

import java.io.File

/**
 * @author Tobias Behr
 */
interface FileReader<T> {
    fun read(file : File) : T
}