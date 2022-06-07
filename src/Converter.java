
import javax.swing.JOptionPane;

public class Converter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Dá as opções do programa
		Object[] itens = {"Conversor de Moeda", "Conversor de Temperatura"};
		Object selectedValue = JOptionPane.showInputDialog(null, "Escolha um item", "Opção", JOptionPane.INFORMATION_MESSAGE, null, itens, itens[0]);
		String opcao = String.valueOf(selectedValue);
		
		switch (opcao) {
			case "Conversor de Moeda":
				// consegue o codigo das moedas para conversao
				String[] codigoParaConversao = UtilsMoeda.codigoDasMoedas();
				
				// pega o código da moeda destino de conversão
				String codigoMoedaBase = codigoParaConversao[0].substring(4, 7);
				
				// Pede o valor base de conversão
				Double valorMoedaAtual = UtilsMoeda.ObterValorDeMoeda(codigoParaConversao[1]);				
				
				// processa string devolvido pela api para extrair o valor de conversao 
				String output = UtilsMoeda.ValorDeConversao(codigoParaConversao[0]);
				int positionOfSemiColon = output.indexOf(":")+1;
				int positionOfClosingBracket = output.indexOf("}");
				String rawValueString = output.substring(positionOfSemiColon, positionOfClosingBracket);
				Double convertedValue = Double.parseDouble(rawValueString);
				
				// multiplica pelo valor fornecido pelo usuário, formata a mensagem e devolve para o usuário
				Double valorConvertido = convertedValue * valorMoedaAtual;
				String valorConvertidoFormatdo = String.format("%.2f", valorConvertido);
				String mensagem = "O valor convertido deu: " + codigoMoedaBase + " " + valorConvertidoFormatdo;
				JOptionPane.showMessageDialog(null, mensagem);
				break;
			case "Conversor de Temperatura":
				// obtem os nomes das temperaturas		
				String[] baseDestino = UtilsTemperatura.temperaturaBaseEDestino();
				String dePara = baseDestino[0] + "_" + baseDestino[1];
				
				// obtem o valor da temperatura
				double temperaturabase = UtilsTemperatura.ObterValorDaTemperatura(baseDestino[0]);
				
				// faz a conversao dos valores e mostra para o usuário
				double resultado = UtilsTemperatura.conversor(dePara, temperaturabase);
				String resposta = "O valor convertido deu: " + String.format("%.2f", resultado);
				JOptionPane.showMessageDialog(null, resposta);
				break;
		}
		
		int continuar = JOptionPane.showConfirmDialog(null,"Deseja continuar?","Escolha um",JOptionPane.YES_NO_CANCEL_OPTION);

		if (continuar == 0) {
			main(args);
		} else if (continuar == 1) {
			JOptionPane.showMessageDialog(null, "Programa finalizado");
		} else {
			JOptionPane.showMessageDialog(null, "Programa concluído");
		}
	}

}
