<template>
    <div>
        <h2>Change password</h2>
        <hr>
        <div class="row">
            <form class="col-md-8">
                <div class="form-group">
                    <label class="control-label" for="oldPassword">Old password</label>
                    <input type="password" name="oldPassword" class="form-control" id="oldPassword" maxlength="255"
                           v-model="oldPassword">
                    <span class="help-block"></span>
                </div>
                <div class="form-group">
                    <label class="control-label" for="newPassword">New password</label>
                    <input type="password" name="newPassword" class="form-control" id="newPassword" maxlength="255"
                           v-model="newPassword">
                    <span class="help-block"></span>
                </div>
                <div class="form-group">
                    <label class="control-label" for="confirmedNewPassword">Confirm new password</label>
                    <input type="password" name="confirmedNewPassword" class="form-control" id="confirmedNewPassword"
                           maxlength="255" v-model="confirmNewPassword">
                    <span class="help-block"></span>
                </div>
                <div class="form-group">
                    <button type="button" class="btn btn-default" @click="updatePassword">Update password</button>
                    <button type="button" class="btn btn-link" data-toggle="tooltip" data-placement="right"
                            title="what a shame">I forgot my password
                    </button>
                </div>
            </form>
        </div>

        <h2>Change username</h2>
        <hr>
        <div class="row">
            <form class="col-md-8">
                <div class="form-group">
                    <button type="button" class="btn btn-default" data-toggle="modal"
                            data-target="#changeUsernameModal">
                        Change Username
                    </button>
                </div>
            </form>
        </div>

        <h2 class="text-danger">Delete account</h2>
        <hr>
        <div class="row">
            <form class="col-md-8">
                <div class="form-group">
                    <button type="button" class="btn btn-default btn-danger" data-toggle="modal"
                            data-target="#deleteAccountConfirm">
                        Delete your account
                    </button>
                </div>
            </form>
        </div>

        <div class="modal fade  bs-example-modal-sm" id="changeUsernameModal" tabindex="-1" role="dialog">
            <div class="modal-dialog  modal-sm" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Enter a new username</h4>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="form-group">
                                <input type="text" name="username" class="form-control" maxlength="255"
                                       v-model="username">
                            </div>
                            <div class="form-group">
                                <button type="button" class="btn btn-block btn-success" @click="changeUsername">
                                    Change my username
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="deleteAccountConfirm" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Are you sure you want to do this?</h4>
                    </div>
                    <div class="modal-body">
                        <div class="alert alert-danger" role="alert">
                            <span class="glyphicon glyphicon-exclamation-sign"></span> This is extremely important.
                        </div>
                        <p>
                            We will immediately delete all of your information, including all of your order histories, book comments and your profile.</p>
                        <p>
                            You will no longer receive newsletters and other notifications, and your username will be available to anyone on Naïve Book Store.</p>
                        <hr>
                        <form>
                            <div class="form-group">
                                <label for="usernameConfirm">Your username</label>
                                <input type="text" name="username" class="form-control" maxlength="255"
                                       id="usernameConfirm" v-model="usernameConfirm">
                            </div>
                            <div class="form-group">
                                <label>To verify, type <i>delete my account</i> below:</label>
                                <input type="text" class="form-control" maxlength="255"
                                       v-model="verifyMessage">
                            </div>
                            <div class="form-group">
                                <label for="passwordConfirm">Confirm your password</label>
                                <input type="password" name="password" class="form-control" maxlength="255"
                                       id="passwordConfirm" v-model="passwordConfirm">
                            </div>
                            <div class="form-group">
                                <button type="button" class="btn btn-block btn-success" @click="deleteAccount">
                                    Delete this account
                                </button>
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
        name: 'account-form',
        data: function () {
            return {
                oldPassword: '',
                newPassword: '',
                confirmNewPassword: '',
                username: '',
                usernameConfirm: '',
                verifyMessage: '',
                passwordConfirm: ''
            }
        },
        created: function () {
            $(function () {
                $('[data-toggle="tooltip"]').tooltip()
            })
        },
        methods: {
            updatePassword: function () {
                if (this.newPassword !== this.confirmNewPassword) {
                    return
                }

                const self = this;
                $.ajax({
                    url: '/user/account/password',
                    type: 'PUT',
                    data: {
                        oldPassword: self.oldPassword,
                        newPassword: self.newPassword
                    },
                    success: function (data) {
                        if (data === true) {
                            window.location.href = '/logout'
                        }
                    }
                })
            },
            changeUsername: function () {
                const self = this;
                $.ajax({
                    url: '/user/account/username',
                    type: 'PUT',
                    data: {
                        username: self.username
                    },
                    success: function (data) {
                        if (data === true) {
                            window.location.href = '/logout'
                        }
                    }
                })
            },
            deleteAccount: function () {
                if (this.verifyMessage !== "delete my account") {
                    return
                }

                const self = this;
                $.ajax({
                    url: '/user/account/delete',
                    type: 'POST',
                    data: {
                        username: this.usernameConfirm,
                        password: this.passwordConfirm
                    },
                    success: function (data) {
                        if (data === true) {
                            window.location.href = '/logout'
                        }
                    }
                })
            }
        }
    }
</script>