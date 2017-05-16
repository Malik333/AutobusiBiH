package com.alenmalik.autobusibih;

/**
 * Created by korisnik on 16/05/2017.
 */

    public class Transport {

        String logo;
        String name;
        String address;
        String phoneNumber;
        String website;

        public Transport() {
        }

        public Transport(String logo, String name, String address, String phoneNumber, String website) {
            this.logo = logo;
            this.name = name;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.website = website;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }
    }

