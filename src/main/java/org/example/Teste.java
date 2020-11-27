package org.example;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import java.util.List;

public class Teste {

  public static void main(String[] args) {
    // Necessario criar essa instancia, ele é a classe que inicializa seu bot
    // Necessario passar um token como argumento do construtor
    TelegramBot bot = new TelegramBot("AQUI VAI SEU TOKEN");

    // Aqui estamos executando o bot e recebendo as mensagens do chat do bot
    GetUpdatesResponse updatesResponse = bot.execute(new GetUpdates());

    // lista dos resultados do chat do bot
    List<Update> listaDeMensagensDoChat = updatesResponse.updates();

    // estou pegando sempre a ultima mensagem enviada
    Update update = listaDeMensagensDoChat.get(listaDeMensagensDoChat.size() - 1);

    // estou criando o obejto message que ta sendo criada atraves do retorno do nosso objeto acima
    Message message = update.message();

    // estou pegando qual chat foi recebida a ultima mensagem
    Chat chat = message.chat();

    // estou pegando o texto que o usuario digitou no chat do bot
    String msgRecebida = message.text();

    // estou exibindo no console, validando se o nosso programa aqui recebeu a mensagem
    System.out.printf("RECEBI SUA MENSAGEM: %s", !msgRecebida.isEmpty() ? "SIM" : "NÃO");

    // estou exibindo no console, qual mensagem o usuario digitou no chat que tem o bot registrado
    System.out.printf("\nMensagem Recebida: %s", msgRecebida);


    // aqui estou enviando para o chat, uma acao do bot digitando algo
    // isso aqui serve para efeito de melhorar a experiencia do usuario
    // como que o usuario sabe que o bot recebeu a mensagem
    // é atraves desse linha abaixo que manda um efeito visual
    // simulando que o bot esta digitando algo
    bot.execute(new SendChatAction(chat.id(), ChatAction.typing.name()));

    // e aqui de fato o bot responde, aqui pode ser colocada a informação que voce deseja
    bot.execute(new SendMessage(chat.id(), "Parabens, esta funcionando"));

  }

}
