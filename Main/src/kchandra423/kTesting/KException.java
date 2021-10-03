package kchandra423.kTesting;

class KException extends RuntimeException {

    protected KException(String message) {
        super("\n\n\n***************\nYour code failed! :(" +
                "\n" + message + "\n***************\n\n\n");
    }

}
