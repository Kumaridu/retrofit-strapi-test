package model;

import java.util.List;

public class GuideLine {
    public GuideLineAttr attributes;

    public static class GuideLineAttr extends Attribute {
        public List<GuideLineItem> guidelineItems;
    }

    public static class GuideLineItem {
        public String title;
        public String content;
    }
}
