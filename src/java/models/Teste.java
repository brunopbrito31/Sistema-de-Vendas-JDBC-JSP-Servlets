/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import models.entities.dao.FabricaDAO;
import models.entities.dao.ItemEstoqueDAO;
import models.entities.dao.ProdutoDAO;
import models.entities.dao.UsuarioDAO;
import models.entities.dao.VendaDAO;

/**
 *
 * @author bruno
 */
//Classe para testar componentes
public class Teste {
    
//    public static void main(String[] args){
//        boolean teste = FabricaDAO.criarUsuarioDAO().validarUsuario("admin", "admin");
//        
//        System.out.println("Resultado = "+teste);
//    }
    
    public static void main(String[] args){
        
        ProdutoDAO produtoDAO = FabricaDAO.criarProdutoDAO();
        UsuarioDAO usuarioDAO = FabricaDAO.criarUsuarioDAO();
        ItemEstoqueDAO itemEstoqueDAO = FabricaDAO.criarItemEstoqueDAO();
        VendaDAO vendaDAO = FabricaDAO.criarVendaDAO();
        
        
        System.out.println("Item trazido: "+itemEstoqueDAO.buscarPeloId(6));
        
        
//        String codBarras = "658900003";
////      ProdutoDAO produtoDAO = FabricaDAO.criarProdutoDAO();
//        List<Produto> produtosEncontrados = produtoDAO.buscarTodosOsProdutosCFiltro("ca",codBarras,"");
//        if(!produtosEncontrados.isEmpty()){
//            System.out.println("Nao vazio: "+produtosEncontrados);
//        }else{
//            System.out.println("Vazio: "+produtosEncontrados);
//        }

        
        
        
     

        

        
        

//        Scanner reader = new Scanner(System.in);;
//        System.out.print("Digite o email do destinatário: ");
//        String email = reader.nextLine().trim();
////        String email ="brunopbrito31@hotmail.com";
////        System.out.print("Digite o assunto do email: ");
////        String assunto = reader.nextLine();
//        System.out.print("Digite a mensagem do email: ");
//        String msg = reader.nextLine();
//        
//            StringBuilder sb = new StringBuilder();
//            String assunto = sb.append("100 ª tentativa de contato").toString();
//            GeradorEmail.enviarEmail(email,msg,assunto);
    }
    
}

// Geração de gráfico 

//<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
//            <script type="text/javascript">
//              google.charts.load("current", {packages:['corechart']});
//              google.charts.setOnLoadCallback(drawChart);
//              function drawChart() {
//                var data = google.visualization.arrayToDataTable([
//                  ["Element", "Density", { role: "style" } ],
//                  ["Copper", 8.94, "#b87333"],
//                  ["Silver", 10.49, "silver"],
//                  ["Gold", 19.30, "gold"],
//                  ["Platinum", 21.45, "color: #e5e4e2"]
//                ]);
//
//                var view = new google.visualization.DataView(data);
//                view.setColumns([0, 1,
//                                 { calc: "stringify",
//                                   sourceColumn: 1,
//                                   type: "string",
//                                   role: "annotation" },
//                                 2]);
//
//                var options = {
//                  title: "Density of Precious Metals, in g/cm^3",
//                  width: 600,
//                  height: 400,
//                  bar: {groupWidth: "95%"},
//                  legend: { position: "none" },
//                };
//                var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
//                chart.draw(view, options);
//            }
//            </script>
//        <div id="columnchart_values" style="width: 900px; height: 300px;"></div>
