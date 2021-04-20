package network.packets;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import network.PacketInfo;
import util.Console;
import util.Encoding;
import util.encoding.ASCII;

public class LoginPacket extends DataPacket {
    @Expose
    public static String NETWORK_ID = PacketInfo.LOGIN_PACKET;
    @Expose
    public String guid;
    @Expose
    public String username;
    @Expose
    public String password;
    @Expose
    public String arguments;
    @Expose
    public String macAddress;

    @Override
    public String pid() {
        return NETWORK_ID;
    }
    // BF7FDFB69342B376BEC675A1046B63256E12904B9536955558E6B22C53DB9012AA3C864E9D20A8FD5562A892E126AF7219321BB3891CAF66EF137AFCF55E9701A1FEFB29EE14EDFA8DAD9FA1D12BA2527E5C8AD68F6B860C5AA089F51E578766A7CCB58B5D62CED2B5D0430977E
    @Override
    public void decode() {
        // Console.PrintBytes(getBuffer(), "LoginPacket");
        username = ASCII.String(buffer, 16, 20).replace(Encoding.getString(new byte[] { 0x0 }), "").trim();
        password = ASCII.String(buffer, 37, 20).replace(Encoding.getString(new byte[] { 0x0 }), "").trim();
        guid = Encoding.getString(buffer,77, 32).replace(Encoding.getString(new byte[] { 0x0 }), "").trim();
        arguments = Encoding.getString(buffer, 145, 12).replace(Encoding.getString(new byte[] { 0x0 }), "").trim();
        macAddress = Encoding.getString(buffer, 407, 12).replace(Encoding.getString(new byte[] { 0x0 }), "").trim();
        debug();
    }

    @Override
    public void encode() {

    }

    public void debug() {
        Console.Log(new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create().toJson(this).toString());
    }
}
