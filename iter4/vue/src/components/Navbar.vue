<template>
    <div>
        <!-- navbar part -->
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container">

                <!-- nav title -->
                <div class="navbar-header">
                    <a class="navbar-brand" href="/">Naïve Book Store</a>
                </div>

                <!-- nav tag and search box -->
                <div class="navbar-collapse collapse">

                    <!-- tags -->
                    <ul class="nav navbar-nav navbar-right">
                        <!-- home -->
                        <li :class="{ active: activeTag === 'home' }"><a href="/">Home</a></li>
                        <!-- cart -->
                        <li :class="{ active: activeTag === 'cart' }">
                            <a href="/my_cart.html">Cart <span class="badge">{{ cartSize }}</span></a>
                        </li>
                        <!-- account -->
                        <li :class="{ active: activeTag === 'space', dropdown: loggedIn }">
                            <template v-if="loggedIn">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                   aria-haspopup="true" aria-expanded="false">Account <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="/space.html">My space</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="#" @click="logout">Log out</a></li>
                                </ul>
                            </template>
                            <template v-else>
                                <a data-toggle="modal" data-target="#loginModal" href="#">Log in</a>
                            </template>
                        </li>
                    </ul>

                    <!-- search box -->
                    <form class="navbar-form navbar-right" v-if="activeTag === 'home'" @submit.prevent="search">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Search books" name="q"
                                   v-model="keyword">
                            <span class="input-group-btn">
                                <button id="searchButton" class="btn btn-default" type="button"
                                        @click="$emit('queryBook', keyword)">Go!</button>
                            </span>
                        </div>
                    </form>
                </div>
            </div>
        </nav>

        <!-- login modal associated with above navbar-->
        <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" id="loginModal">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <!-- header -->
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h3 class="modal-title">Log in</h3>
                    </div>

                    <!-- form -->
                    <div class="modal-body">
                        <form action="/login" method="post">
                            <div class="form-group">
                                <label class="control-label" for="username">Username</label>
                                <input type="text" class="form-control" id="username" name="username"
                                       aria-describedby="username-error">
                                <span id="username-error" class="help-block"></span>
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="password">Password</label>
                                <input type="password" class="form-control" id="password" name="password"
                                       aria-describedby="password-error">
                                <span id="password-error" class="help-block"></span>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary btn-block">Log in</button>
                            </div>
                            <div class="form-group">
                                <div class="well">
                                    New to Naïve Book Store? <a href="/signup.html">Create an account.</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'navbar',
        props: ['activeTag', 'loggedIn'],
        data: function () {
            return {
                cartSize: 0,
                keyword: ''
            }
        },
        watch: {
            loggedIn: function (newLoggedIn) {
                if (newLoggedIn === true) {
                    this.updateCartSize();
                }
            }
        },
        methods: {
            updateCartSize: function () {
                const self = this;
                $.get('/user/cart/size', function (data) {
                    if (typeof data === 'number') {
                        self.cartSize = data;
                    }
                });
            },
            logout: function (event) {
                $.post('/logout', function () {
                    window.location.href = '/index.html';
                });
            },
            search: function (event) {
                $('#searchButton').click();
            }
        }
    }
</script>