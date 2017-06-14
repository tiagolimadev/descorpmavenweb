/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.bean;

import com.ifpe.tads.descorp.excecao.ExcecaoNegocio;
import com.ifpe.tads.descorp.model.produto.Produto;
import com.ifpe.tads.descorp.model.usuario.Cliente;
import com.ifpe.tads.descorp.model.venda.ItemVenda;
import com.ifpe.tads.descorp.model.venda.Venda;
import com.ifpe.tads.descorp.servico.ClienteServico;
import com.ifpe.tads.descorp.servico.ProdutoServico;
import com.ifpe.tads.descorp.servico.UsuarioServico;
import com.ifpe.tads.descorp.servico.VendaServico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.Valid;

/**
 *
 * @author eduardo.amaral
 */
@SessionScoped
@ManagedBean(name = "clienteBean")
public class ClienteBean extends BasicBean implements Serializable {
    
    
    
    @Valid
    private Cliente cliente;

    @EJB
    private ClienteServico clienteServico;
    
    @EJB
    private ProdutoServico produtoServico;
    
    @EJB
    private VendaServico vendaServico;
    
    @EJB
    private UsuarioServico usuarioServico;
    
    @Valid
    private Venda vendaSelecionada;
    
    @Valid
    private Venda novaVenda;
    
    @Valid
    private ItemVenda novoItem;
    
    private List<Produto> produtosDisponiveis;
    
    @PostConstruct
    public void init(){
        cliente = (Cliente) usuarioServico.getUsuarioPorId(1L);
    }
    
    public ClienteBean() {}
    
    public String initHistorico(){
        String resultado = "";
        
        cliente = clienteServico.getHistorico(cliente);
        
        resultado = "historico-cliente";
        
        return resultado;
    }

    public String initNovaCompra(){
        String nav = "nova-compra";
        
        novaVenda = new Venda();
        novaVenda.setCliente(cliente);
        novaVenda.setItensVenda(new ArrayList<ItemVenda>());
        
        produtosDisponiveis = produtoServico.getProdutos();
        
        return nav;
    }
    
    public String finalizarCompra(){
        String nav = "home-cliente";
        
        try{
            vendaServico.finalizarVenda(novaVenda);
        } catch (ExcecaoNegocio e){
            nav = "";
            super.adicionarMessagem(FacesMessage.SEVERITY_WARN, e.getMessage());
        }
        
        return nav;
    }
    
    public void adicionarItem(){
        novoItem.copiarPreco();
        novaVenda.getItensVenda().add(novoItem);
    }
    
    public void initNovoItem(){
        novoItem = new ItemVenda();
        novoItem.setProduto(new Produto());
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Venda getVendaSelecionada() {
        return vendaSelecionada;
    }

    public void setVendaSelecionada(Venda vendaSelecionada) {
        this.vendaSelecionada = vendaSelecionada;
    }

    public Venda getNovaVenda() {
        return novaVenda;
    }

    public void setNovaVenda(Venda novaVenda) {
        this.novaVenda = novaVenda;
    }

    public ItemVenda getNovoItem() {
        return novoItem;
    }

    public void setNovoItem(ItemVenda novoItem) {
        this.novoItem = novoItem;
    }

    public List<Produto> getProdutosDisponiveis() {
        return produtosDisponiveis;
    }

    public void setProdutosDisponiveis(List<Produto> produtosDisponiveis) {
        this.produtosDisponiveis = produtosDisponiveis;
    }
    
}
