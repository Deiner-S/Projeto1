package Util;

public class ValidacaoUtil {

    public Long converterCpf(String cpf){

        String stringCpf = cpf.replaceAll("\\D","");

        return Long.parseLong(stringCpf);
    }

    public boolean isValidCpf(String cpf){
        return cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }

    public boolean isValidEmail(String email){
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }
    
    public boolean isValidCampo(String ...campo){
        for(String e: campo){
            if(e == null || e.equals("")){
                return false;
            }
        }
        return true;
    }

}
