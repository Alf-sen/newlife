package zxs.up.newlife.cache;

import zxs.up.newlife.dto.TagDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @auther ZhangXiusen
 * @date 2020/03/01 10:59
 */
public class TagCache {
    public static List<TagDTO> getTag() {
        List<TagDTO> tagDTOList = new ArrayList<>();
        TagDTO life = new TagDTO();
        life.setCategoryName("生活");
        life.setTagList(Arrays.asList("卫生", "购物", "健身", "音乐", "影视", "美食", "游戏", "聚会", "着装", "养生", "宠物", "亲情", "友情", "爱情"));
        TagDTO sports = new TagDTO();
        sports.setCategoryName("体育");
        sports.setTagList(Arrays.asList("篮球", "足球", "羽毛球", "台球", "乒乓球", "游泳", "跑步", "网球", "跳高", "跳远", "铅球", "铁饼", "标枪", "越野"));
        TagDTO work = new TagDTO();
        work.setCategoryName("工作");
        work.setTagList(Arrays.asList("Java", "Python", "C", "C++", "Spring", "Spring Boot", "Spring MVC", "MySql", "Oracle", "H2", "Html", "Java Script", "CSS", "JQuery", "Redis", "Kafka"));

        tagDTOList.add(life);
        tagDTOList.add(sports);
        tagDTOList.add(work);

        return tagDTOList;
    }
}
