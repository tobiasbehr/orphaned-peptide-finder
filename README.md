# Orphaned Peptide Finder App

## Purpose
The `Orphaned Peptide Finder (OPF)` enables the comparison between peptide lists (as csv)
and protein databases (as fasta). This allows the identification and output of peptides 
which are not present in the protein database, primarily the identification of peptides 
which were identified in a proteomic approach applying a six-frame translated database 
of Open Reading Frames (ORFs). These can then be used to improve genomic annotations 
(proteogenomics). OPF is fast, simple, and reliable as well as protein digestion method 
independent.  

## Run the program

To run the program, download and doubleclick the jar-file from the project root.

## Build from sources

Requirements
* JDK 1.8
* Maven 3.3.9

To build the project with maven:

`mvn clean package`

A `jar` with the suffix `jar-with-dependencies` will be created in the `target/` folder.

This can be executed on the command line:

`java -jar target/orphan-peptide-finder-1.0.0-jar-with-dependencies.jar`