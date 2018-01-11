<template>
    <div class="container user-info">
        <br/>
        <br/>
        <div class="row">
            <div class="col-md-4 col-md-push-4">
                <img class="img-circle" :src="'/image/avatar/' + profile.avatarFilename" width="150" height="150">

                <h1>{{ user.username }}</h1>
                <p class="lead">
                    {{ profile.bio }}
                    <span class="text-muted" v-if="profile.location "><i>at</i></span>
                    {{ profile.location }}
                </p>
                <p>{{ user.email }}</p>
                <p>
                    <span class="label label-info">${{ user.credit / 100 }}</span>
                    <span v-if="user.isAdmin" class="label label-info">Admin</span>
                </p>
            </div>
        </div>
        <br/>
    </div>
</template>

<script>
    export default {
        name: 'profile-banner',
        props: ['user', 'profile']
    }
</script>

<style>
    .user-info .col-md-4 {
        margin-bottom: 20px;
        text-align: center;
    }

    .user-info h2 {
        font-weight: normal;
    }

    .user-info .col-md-4 p {
        margin-right: 10px;
        margin-left: 10px;
    }
</style>