import Vue from 'vue';
import Router from 'vue-router';
import Index from './views/Index.vue';
import Signup from './views/Signup.vue';
import Login from './views/Login.vue';
import Profile from './views/Profile.vue';
import MainNavbar from './layout/MainNavbar.vue';
import MainFooter from './layout/MainFooter.vue';
import Notice from './views/Notice.vue';
import ReadNotice from './views/ReadNotice.vue';
import UpdateArticle from './views/UpdateArticle.vue';
import RegisterArticle from './views/RegisterArticle.vue';
import QnA from './views/QnA.vue';
import Select from './views/Select.vue';
import ReadQnA from './views/ReadQnA.vue';

Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'index',
      components: { default: Index, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/select',
      name: 'select',
      components: { default: Select, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: {backgroundColor: 'back'},
      }
    },
    {
      path: '/signup',
      name: 'signup',
      components: { default: Signup, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
      },
    },
    {
      path: '/login',
      name: 'login',
      components: { default: Login, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
      },
    },
    {
      path: '/profile',
      name: 'profile',
      components: { default: Profile, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/notice',
      name: 'notice',
      components: { default: Notice, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/readNotice',
      name: 'readNotice',
      components: { default: ReadNotice, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/updateArticle',
      name: 'updateArticle',
      components: { default: UpdateArticle, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/registerArticle',
      name: 'registerArticle',
      components: { default: RegisterArticle, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/qna',
      name: 'qna',
      components: { default: QnA, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
    {
      path: '/readQnA',
      name: 'readQnA',
      components: { default: ReadQnA, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' },
      },
    },
  ],
  scrollBehavior: (to) => {
    if (to.hash) {
      return { selector: to.hash };
    } else {
      return { x: 0, y: 0 };
    }
  },
});
