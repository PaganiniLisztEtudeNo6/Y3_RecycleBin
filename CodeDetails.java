class CodeDetails {
    private int classCount = 0;
    private int methodCount = 0;
    private int variableCount = 0;
    private StringBuilder classDetails = new StringBuilder();
    private StringBuilder methodDetails = new StringBuilder();
    private StringBuilder variableDetails = new StringBuilder();

    public void addClass(String className) {
        classCount++;
        classDetails.append("\n\nClass: ").append(className).append("\n");
    }

    public void addMethod(String returnType, String methodName) {
        methodCount++;
        methodDetails.append("Method: ").append(returnType).append(" ").append(methodName).append("\n");
    }

    public void addVariable(String methodName, String variableType, String variableName) {
        variableCount++;
        variableDetails.append("Method: ").append(methodName).append(", Variable: ").append(variableType).append(" ")
                .append(variableName).append("\n");
    }

    public int getClassCount() {
        return classCount;
    }

    public int getMethodCount() {
        return methodCount;
    }

    public int getVariableCount() {
        return variableCount;
    }

    public void printDetails() {
        System.out.println(classDetails.toString() + "\n");
        System.out.println(methodDetails.toString() + "\n");
        System.out.println(variableDetails.toString() + "\n");
    }
}
