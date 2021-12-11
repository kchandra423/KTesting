package kchandra423.kTesting.kExceptions;

class KException extends RuntimeException {

    protected KException(String message) {
        super("\n\n\n***************\nYour code failed! :(" +
                "\n" + message + "\n***************\n\n\n");
    }

}
