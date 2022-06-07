import javax.swing.JOptionPane;

public class UtilsTemperatura {
	
	public static double ObterValorDaTemperatura(String nomeDaTemperaturaBase) {
		
		String inputValue;
		
		// regex para se certificar que string pode ser convertida em Double. Aceita valores positivos e negativos	
		String testeDouble = "(\\-?\\d*\\,?\\d+)|(\\-?\\d*\\.?\\d+)";
		
		// Loop que obtém um valor através de um input
		do { 
			inputValue = JOptionPane.showInputDialog("Por favor insira o valor da temperatura em " + nomeDaTemperaturaBase);
			if (!inputValue.matches(testeDouble)) {
				JOptionPane.showMessageDialog(null, "Precisa fornecer um número para ser computado", "AVISO", JOptionPane.ERROR_MESSAGE);
			}
		} 
		while (!inputValue.matches(testeDouble));
		
		// aceita valor de moeda independente se for escrito com ponto ou vírgula
		if (!(inputValue.indexOf(',') == -1)) {
			inputValue = inputValue.replace(',', '.');
		}
		
		// retorna o valor
		return Double.parseDouble(inputValue);
		
	}
	
	public static String[] temperaturaBaseEDestino() {
		
		String [] temperaturas = UtilsTemperatura.temperaturas();
		Object temperaturaBase = JOptionPane.showInputDialog(null, "Converter de", "Opção", JOptionPane.INFORMATION_MESSAGE, null, temperaturas, temperaturas[0]);
		
		Object temperaturaDestino;
		
		do {
			temperaturaDestino = JOptionPane.showInputDialog(null, "Converter para", "Opção", JOptionPane.INFORMATION_MESSAGE, null, temperaturas, temperaturas[0]);
			if (temperaturaDestino == temperaturaBase) {
				JOptionPane.showMessageDialog(null, "Precisa escolher uma escala diferente", "AVISO", JOptionPane.ERROR_MESSAGE);
			}
		} while (temperaturaBase == temperaturaDestino);
		
		return new String[] {String.valueOf(temperaturaBase), String.valueOf(temperaturaDestino)};
	}
	
	public static String[] temperaturas(){
		return new String[] {"Kelvin", "Celsius", "Fahrenheit"};
	}
	
	public static double conversor(String dePara, double medicao) {
		double resultado = 0;
		switch (dePara) {
			case "Celsius_Kelvin":
				resultado = medicao + 273;
				break;
			case "Celsius_Fahrenheit":
				resultado = (medicao*9/5)+32;
				break;
			case "Kelvin_Celsius":
				resultado = medicao - 273;
				break;
			case "Kelvin_Fahrenheit":
				resultado = (medicao - 273)*9/5 + 32;
				break;
			case "Fahrenheit_Celsius":
				resultado = 5*((medicao-32)/9);
				break;
			case "Fahrenheit_Kelvin":
				resultado = 273 + (medicao-32)*5/9;
				break;
		}
		return resultado;
	}

}
