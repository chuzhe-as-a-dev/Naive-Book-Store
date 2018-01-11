<template>
    <tr>
        <th scope="row">{{ index }}</th>
        <td>{{ username }}</td>
        <td>
            <div v-if="editing">
                <div class="input-group input-group-sm">
                    <input type="text" class="form-control" v-model="newAddress">
                    <span class="input-group-btn">
                    <button class="btn btn-default" @click="updateAddress">
                        <span class="glyphicon glyphicon-refresh"></span>
                    </button>
                    </span>
                </div>
            </div>
            <div v-else>{{ address }}</div>
        </td>
        <td>
            <button class="btn btn-xs btn-default" @click="editing = !editing">Edit</button>
            <button class="btn btn-xs btn-danger" @click="removeAddress">Remove</button>
        </td>
    </tr>
</template>

<script>
    export default {
        name: 'address-list-item',
        props: ['address', 'index', 'username'],
        data: function () {
            return {
                editing: false,
                newAddress: ''
            }
        },
        created: function () {
            this.newAddress = this.address;
        },
        watch: {
            address: function (address) {
                this.newAddress = address;
            }
        },
        methods: {
            removeAddress: function (event) {
                const self = this;
                $.ajax({
                    url: '/user/profile/address/' + this.index,
                    type: 'DELETE',
                    success: function (data) {
                        if (data === true) {
                            self.$emit('addressUpdated');
                        }
                    }
                });
            },
            updateAddress: function (event) {
                const self = this;
                $.ajax({
                    url: '/user/profile/address',
                    type: 'PUT',
                    data: {
                        index: this.index,
                        address: this.newAddress
                    },
                    success: function (data) {
                        if (data === true) {
                            self.editing = false;
                            self.$emit('addressUpdated');
                        }
                    }
                });
            }
        }
    }
</script>