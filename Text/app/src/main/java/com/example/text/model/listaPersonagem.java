package com.example.text.model;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.text.R;
import com.example.dao.PersonagemDAO;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.example.text.model.ConstantesActivities.CHAVE_PERSONAGEM;

public class listaPersonagem extends AppCompatActivity{


    public static final String TITULO_APPBAR = "Formulario de Personagens";

    private final PersonagemDAO dao=new PersonagemDAO();

    //Criando um override para a lista de personagens
    @Override
    protected void onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        setContentView(R.layout.activity_listapersonagem);

        //Setando o nome
        setTitle(TITULO_APPBAR);

        configuraFabNovoPersonagem();


    }

    private void configuraFabNovoPersonagem() {
        FloatingActionButton botaoNovoPersonagem=findViewById(R.id.fab_add);
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreFormulario();
            }
        });
    }

    private void abreFormulario() {
        startActivity(new Intent(this, formularioPersonagem.class));
    }

    //fazendo uma proteçao para os dados
    @Override
    protected void onResume() {
        super.onResume();
        ListView listaDePersonagens=findViewById(R.id.activity_lista_personagem);
        //referencia ao dao.todos()
        final List<Personagem> personagens = dao.todos();
        //steando os personagens na lista
        listaDePersonagens(listaDePersonagens, personagens);

        configuraItemPorClique(listaDePersonagens);


    }

    private void configuraItemPorClique(ListView listaDePersonagens) {
        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            //Metodo para seleçao do personagem
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Personagem personagemEscolhido = (Personagem) adapterView.getItemAtPosition(position);
                abreFormularioEditar(personagemEscolhido);

            }
        });
    }

    private void abreFormularioEditar(Personagem personagemEscolhido) {
        //Entrando no formulario novamente
        Intent vaiParaFormulario = new Intent(listaPersonagem.this, formularioPersonagem.class);
        vaiParaFormulario.putExtra(CHAVE_PERSONAGEM, personagemEscolhido);
        startActivity(vaiParaFormulario);
    }

    private void listaDePersonagens(ListView listaDePersonagens, List<Personagem> personagens) {
        listaDePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagens));
    }
}
