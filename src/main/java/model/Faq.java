package model;

import java.util.List;

public class Faq {
    public FaqAttr attributes;

    public static class FaqAttr extends Attribute {
        public List<FaqItem> faqItems;
    }

    public static class FaqItem {
        public String title;
        public List<QuestionAndAnswer> questionAnswer;

        public static class QuestionAndAnswer {
            public String question;
            public String answer;
        }
    }


}
