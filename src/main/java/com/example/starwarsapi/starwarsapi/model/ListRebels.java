package com.example.starwarsapi.starwarsapi.model;

import java.util.*;

public class ListRebels {
    private static List<Rebel> rebels = new ArrayList<>();

    public void addRebel(Rebel rebel){
        ListRebels.rebels.add(rebel);
    }

    public List<Rebel> searchRebels(){
        return ListRebels.rebels;
    }

    public Rebel detailsRebel(UUID id) throws Exception {
        Optional<Rebel> resultRebel =
                ListRebels.rebels.stream().filter(rebel -> Objects.equals(rebel.getId(),id)).findAny();
        if(resultRebel.isPresent()){
            return resultRebel.get();
        } else {
            throw new Exception("Rebelde não encontrado.");
        }
    }

//    public Rebel atualizaCliente(UUID id, RequestCliente requestCliente) throws Exception {
//        ListRebels.rebels.stream().filter(cliente -> Objects.equals(cliente.getId(),id))
//                .forEach(cliente -> {
//                    cliente.setNome(requestCliente.getNome());
//                    cliente.setEmail(requestCliente.getEmail());
//                    cliente.setSenha(requestCliente.getSenha());
//                });
//        return detalhesCliente(id);
//    }
}
