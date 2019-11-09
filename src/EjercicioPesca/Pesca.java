/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EjercicioPesca;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 *
 * @author dpinepadi
 */
@XmlRootElement(name = "Pesca")
public class Pesca {

    ArrayList<Especie> listaEspecies;
    ArrayList<Xornada> listaXornadas;

    public Pesca() {
    }

    @XmlElementWrapper(name = "Especies")
    @XmlElement(name = "Especie")
    public ArrayList<Especie> getListaEspecies() {
        return listaEspecies;
    }

    @XmlElement(name = "Xornada")
    public ArrayList<Xornada> getListaXornadas() {
        return listaXornadas;
    }

    public void setListaEspecies(ArrayList<Especie> listaEspecies) {
        this.listaEspecies = listaEspecies;
    }

    public void setListaXornadas(ArrayList<Xornada> listaXornadas) {
        this.listaXornadas = listaXornadas;
    }
}

@XmlType(propOrder = {"habitat", "nomeCientifico", "outrosNomes", "capturaMinima", "notas"})
class Especie {

    String nome, valor, habitat, nomeCientifico, outrosNomes;
    CapturaMinima capturaMinima;
    ArrayList<String> notas;

    public Especie() {
    }

    public void setOutrosNomes(String outrosNomes) {
        this.outrosNomes = outrosNomes;
    }

    @XmlElement(name = "OutrosNomes", required = false)
    public String getOutrosNomes() {
        return outrosNomes;
    }

    @XmlAttribute(name = "nome")
    public String getNombre() {
        return nome;
    }

    @XmlAttribute(name = "valor")
    public String getValor() {
        return valor;
    }

    @XmlElement(name = "Habitat")
    public String getHabitat() {
        return habitat;
    }

    @XmlElement(name = "NomeCientifico")
    public String getNomeCientifico() {
        return nomeCientifico;
    }

    @XmlElement(name = "CapturaMinima")
    public CapturaMinima getCapturaMinima() {
        return capturaMinima;
    }

    @XmlElement(name = "Nota", required = false)
    public ArrayList<String> getNotas() {
        return notas;
    }

    public void setNombre(String nombre) {
        this.nome = nombre;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public void setNomeCientifico(String nomeCientifico) {
        this.nomeCientifico = nomeCientifico;
    }

    public void setCapturaMinima(CapturaMinima capturaMinima) {
        this.capturaMinima = capturaMinima;
    }

    public void setNotas(ArrayList<String> notas) {
        this.notas = notas;
    }
}

@XmlType(propOrder = {"lugar", "data", "capturas", "descripcion"})
class Xornada {

    String lugar, data, descripcion;
    ArrayList<Captura> capturas;

    public Xornada() {
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCapturas(ArrayList<Captura> capturas) {
        this.capturas = capturas;
    }

    @XmlAttribute(name = "lugar")
    public String getLugar() {
        return lugar;
    }

    @XmlAttribute(name = "data")
    public String getData() {
        return data;
    }

    @XmlElement(name = "Descricion", required = false)
    public String getDescripcion() {
        return descripcion;
    }

    @XmlElement(name = "Captura", required = false)
    public ArrayList<Captura> getCapturas() {
        return capturas;
    }
}

class CapturaMinima {

    String unidade, valor;

    public CapturaMinima() {
    }

    @XmlAttribute(name = "unidade")
    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    @XmlValue
    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getUnidade() {
        return unidade;
    }

    public String getValor() {
        return valor;
    }
}

class Captura {

    int numUnidades;
    double peso;
    String valor;

    public Captura() {
    }

    @XmlAttribute(name = "numUnidades")
    public void setNumUnidades(int numUnidades) {
        this.numUnidades = numUnidades;
    }

    @XmlAttribute(name = "peso")
    public void setPeso(double peso) {
        this.peso = peso;
    }

    @XmlValue
    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getNumUnidades() {
        return numUnidades;
    }

    public double getPeso() {
        return peso;
    }

    public String getValor() {
        return valor;
    }
}
