package dev.porcini.remotemobtimer.client;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class RemoteMobTimerClient extends WebSocketClient {


  public RemoteMobTimerClient( URI serverUri , Draft draft ) {
    super( serverUri, draft );
  }

  public RemoteMobTimerClient( URI serverURI ) {
    super( serverURI );
  }

  public RemoteMobTimerClient( URI serverUri, Map<String, String> httpHeaders ) {
    super(serverUri, httpHeaders);
  }

  public static void main( String[] args ) throws URISyntaxException {
    RemoteMobTimerClient c = new RemoteMobTimerClient(new URI("ws://localhost:8887/mob" )); // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
    c.connect();
  }

  @Override
  public void onOpen( ServerHandshake handshakedata ) {
    send("Hello, it is me. Mario :)");
    System.out.println( "opened connection" );
    // if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient
  }

  @Override
  public void onMessage( String message ) {
    System.out.println( "received: " + message );
  }

  @Override
  public void onClose( int code, String reason, boolean remote ) {
    // The codecodes are documented in class org.java_websocket.framing.CloseFrame
    System.out.println( "Connection closed by " + ( remote ? "remote peer" : "us" ) + " Code: " + code + " Reason: " + reason );
  }

  @Override
  public void onError( Exception ex ) {
    ex.printStackTrace();
    // if the error is fatal then onClose will be called additionally
  }

}