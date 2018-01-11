<template>
    <div id="app">
        <navbar activeTag="space" :loggedIn="loggedIn" ref="navbar"></navbar>
        <profile-banner :user="user" :profile="profile"></profile-banner>
        <profile-tab :profile="profile" @bookAdded="updateCartSize"></profile-tab>
        <page-footer></page-footer>
    </div>
</template>

<script>
    import Navbar from "../components/Navbar.vue";
    import ProfileBanner from "./ProfileBanner.vue";
    import ProfileTab from "./ProfileTab.vue";
    import PageFooter from "../components/PageFooter.vue";

    export default {
        components: {
            PageFooter,
            ProfileTab,
            ProfileBanner,
            Navbar
        },
        data: function () {
            return {
                loggedIn: false,
                user: {},
                profile: {avatarFilename: 'default.jpg'}
            }
        },
        created: function () {
            const self = this;
            $.get('/logged_in', function (data) {
                self.loggedIn = (data === true);
            });
            $.get('/user/account', function (data) {
                self.user = data;
            });
            $.get('/user/profile', function (data) {
                self.profile = data;
            })
        },
        methods: {
            updateCartSize: function () {
                this.$refs.navbar.updateCartSize();  // directly ask navbar to update cart size. ugly code tho
            }
        }
    }
</script>

<style>
    body {
        padding-top: 70px;
        padding-bottom: 20px;
    }
</style>
