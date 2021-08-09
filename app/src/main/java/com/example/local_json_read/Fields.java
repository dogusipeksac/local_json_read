package com.example.local_json_read;

    public class Fields
    {
        private String title;
        private String foodCategory;
        private String calori;

        public Fields(String title, String foodCategory, String calori) {
            this.title = title;
            this.foodCategory = foodCategory;
            this.calori = calori;
        }

        public Fields() {
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getFoodCategory() {
            return foodCategory;
        }

        public void setFoodCategory(String foodCategory) {
            this.foodCategory = foodCategory;
        }

        public String getCalori() {
            return calori;
        }

        public void setCalori(String calori) {
            this.calori = calori;
        }
    }
