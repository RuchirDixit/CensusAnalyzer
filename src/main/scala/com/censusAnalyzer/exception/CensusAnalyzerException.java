package com.censusAnalyzer.exception;

public class CensusAnalyzerException extends Exception {
    private Issue error;

    public static enum Issue {
        INCORRECT_FILE, PATH_INCORRECT, INVALID_DELIMITER, INVALID_FIELDS
    }

    public CensusAnalyzerException(Issue error) {
        this.error = error;
    }

    public CensusAnalyzerException() {
    }

    @Override
    public String toString() {
        switch (this.error) {
            case INCORRECT_FILE:
                return "CensusAnalyzerException: File extension is incorrect";
            case PATH_INCORRECT:
                return "CensusAnalyzerException: File Path Incorrect";
            case INVALID_DELIMITER:
                return "CensusAnalyzerException: Delimiter not recognised";
            default:
                return "CensusAnalyzerException: Fields within the file is Invalid";
        }
    }
}
