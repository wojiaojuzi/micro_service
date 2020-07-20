package edge.node.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edge.node.mapper.NodeMapper;
import edge.node.mapper.ServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class MicroService {

    private final ServiceMapper serviceMapper;
    private final NodeMapper nodeMapper;

    @Autowired
    public MicroService(ServiceMapper serviceMapper, NodeMapper nodeMapper){
        this.serviceMapper = serviceMapper;
        this.nodeMapper = nodeMapper;
    }

    public void getImage(String nodeName){
        String ip = nodeMapper.getNodeByNodeName(nodeName).getIp();
        System.out.println(ip);
        try {
            String exe = "python";
            String command = "C:\\Users\\guoxidong\\Desktop\\docketTest\\getimage.py";
            String[] cmdArr = new String[] { exe, command };
            Process process = Runtime.getRuntime().exec(cmdArr);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while( ( line = in.readLine() ) != null ) {
                System.out.println(line);
                //去掉空格   获取各个字段的值
                String[] sp = line.split(" ");
                for(String str : sp)
                    System.out.println(str);
                serviceMapper.updataImageIdByNodeNameAndImageTag(nodeName,sp[2],sp[0]);
                serviceMapper.updataImageShortIdByNodeNameAndImageTag(nodeName,sp[2],sp[1]);
                serviceMapper.updataImageStatusByNodeNameAndImageTag(nodeName,sp[2],true);
            }
            in.close();

            int result = process.waitFor();
            System.out.println("执行结果:" + result);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pullImage(String nodeName){
        String ip = nodeMapper.getNodeByNodeName(nodeName).getIp();
        System.out.println(ip);
        try {
            String exe = "python";
            String command = "C:\\Users\\guoxidong\\Desktop\\docketTest\\getimage.py";
            String[] cmdArr = new String[] { exe, command };
            Process process = Runtime.getRuntime().exec(cmdArr);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while( ( line = in.readLine() ) != null ) {
                System.out.println(line);
            }
            in.close();
            int result = process.waitFor();
            System.out.println("执行结果:" + result);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
