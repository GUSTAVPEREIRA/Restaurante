/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.model;

/**
 *
 * @author Pereira
 */
public class Cliente {
    
    private int cli_id;
    private String cli_nome;
    private String cli_estado_civil;
    private String cli_rg;
    private String cli_cpf;
    private String cli_telefone;
    private String cli_celular;
    private String cli_cep;
    private String cli_cnpj;
    private String clli_ie;
    private Cidade cidade;

    public Cliente() {
    }
    //Cliente do tipo PF
    public Cliente(int cli_id, String cli_nome, String cli_estado_civil, String cli_rg, String cli_cpf, String cli_telefone, String cli_celular, String cli_cep, Cidade cidade) {
        this.cli_id = cli_id;
        this.cli_nome = cli_nome;
        this.cli_estado_civil = cli_estado_civil;
        this.cli_rg = cli_rg;
        this.cli_cpf = cli_cpf;
        this.cli_telefone = cli_telefone;
        this.cli_celular = cli_celular;
        this.cli_cep = cli_cep;
        this.cidade = cidade;
    }
    //Cliente do tipo PJ
    public Cliente(int cli_id, String cli_nome, String cli_telefone, String cli_celular, String cli_cep, String cli_cnpj, String clli_ie, Cidade cidade) {
        this.cli_id = cli_id;
        this.cli_nome = cli_nome;
        this.cli_telefone = cli_telefone;
        this.cli_celular = cli_celular;
        this.cli_cep = cli_cep;
        this.cli_cnpj = cli_cnpj;
        this.clli_ie = clli_ie;
        this.cidade = cidade;
    }

    public int getCli_id() {
        return cli_id;
    }

    public void setCli_id(int cli_id) {
        this.cli_id = cli_id;
    }

    public String getCli_nome() {
        return cli_nome;
    }

    public void setCli_nome(String cli_nome) {
        this.cli_nome = cli_nome;
    }

    public String getCli_estado_civil() {
        return cli_estado_civil;
    }

    public void setCli_estado_civil(String cli_estado_civil) {
        this.cli_estado_civil = cli_estado_civil;
    }

    public String getCli_rg() {
        return cli_rg;
    }

    public void setCli_rg(String cli_rg) {
        this.cli_rg = cli_rg;
    }

    public String getCli_cpf() {
        return cli_cpf;
    }

    public void setCli_cpf(String cli_cpf) {
        this.cli_cpf = cli_cpf;
    }

    public String getCli_telefone() {
        return cli_telefone;
    }

    public void setCli_telefone(String cli_telefone) {
        this.cli_telefone = cli_telefone;
    }

    public String getCli_celular() {
        return cli_celular;
    }

    public void setCli_celular(String cli_celular) {
        this.cli_celular = cli_celular;
    }

    public String getCli_cep() {
        return cli_cep;
    }

    public void setCli_cep(String cli_cep) {
        this.cli_cep = cli_cep;
    }

    public String getCli_cnpj() {
        return cli_cnpj;
    }

    public void setCli_cnpj(String cli_cnpj) {
        this.cli_cnpj = cli_cnpj;
    }

    public String getClli_ie() {
        return clli_ie;
    }

    public void setClli_ie(String clli_ie) {
        this.clli_ie = clli_ie;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    
    
    
}
